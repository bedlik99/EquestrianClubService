package pl.jbed.stud.SomeWebService.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user_code")
public class UserCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "invite_code")
    private String inviteCode;

    @OneToOne(mappedBy = "userCode")
    private User user;

    public UserCode(){}

    public UserCode(String code, User user){
        this.inviteCode= code;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String userCode) {
        this.inviteCode = userCode;
    }

    @Override
    public String toString() {
        return "CustomerCode{" +
                "id=" + id +
                ", userCode='" + inviteCode + '\'' +
                '}';
    }
}
