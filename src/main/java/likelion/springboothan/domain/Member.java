package likelion.springboothan.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
    private Long Id;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList= new ArrayList<>();

    public static Member createMember(String name, Address address){
        Member member=new Member();
        member.name=name;
        member.address=address;
        return member;
    }
}
