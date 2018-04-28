package hu.pte.ttk.des.service;

import hu.pte.ttk.des.entity.Message;
import hu.pte.ttk.des.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    MessageRepository mr;

    @Autowired
    public void setMr(MessageRepository mr) {
        this.mr = mr;
    }

    public Page<Message> getAll(int page, int size) {
        return mr.findAll(PageRequest.of(page - 1, size));
    }

    public void save(Message m) {
        mr.saveAndFlush(m);
    }
}
