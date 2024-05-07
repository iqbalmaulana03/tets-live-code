package com.iqbal.loginsystem.entity;

import com.iqbal.loginsystem.constant.EUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EUser user;
}
