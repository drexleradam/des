package hu.pte.ttk.des.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String plaintext;
    private String key;
    private String ciphertext;

    public Message() {
    }

    public Message(String plaintext, String key, String ciphertext) {
        this.plaintext = plaintext;
        this.key = key;
        this.ciphertext = ciphertext;
    }

    public Message(Long id, String plaintext, String key, String ciphertext) {
        this.id = id;
        this.plaintext = plaintext;
        this.key = key;
        this.ciphertext = ciphertext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }
}
