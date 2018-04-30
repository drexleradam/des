package hu.pte.ttk.des.repository;

import hu.pte.ttk.des.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
