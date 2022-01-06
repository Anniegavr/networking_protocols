package privacy.service.security.services.websocket;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import privacy.service.security.services.OwnerDetailsServiceImpl;

import java.security.Principal;
import java.util.Map;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);
    @Autowired
    private OwnerDetailsServiceImpl ownerDetailsServiceImpl;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String userId = ""+ownerDetailsServiceImpl.getUserIdFromToken();
        LOG.info("User with ID '{}' opened the page", userId);

        return new UserPrincipal(userId);
    }
}
