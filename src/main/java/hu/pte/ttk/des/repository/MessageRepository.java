package hu.pte.ttk.des.repository;

import hu.pte.ttk.des.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
