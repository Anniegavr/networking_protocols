package privacy.dao.websocket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import privacy.models.websocket.WSMessage;

@Repository
@Transactional
public interface WebsocketRepository extends JpaRepository<WSMessage, Long> {
}
