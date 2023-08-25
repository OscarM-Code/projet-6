package com.openclassrooms.mddapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SUBSCRIPTIONS")
@Data
@EqualsAndHashCode(of = { "user", "theme" })
public class Subscription implements Serializable {
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "theme_id")
  private Theme theme;

}
