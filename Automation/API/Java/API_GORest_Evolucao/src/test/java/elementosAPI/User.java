package elementosAPI;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
public class User {
  private enum  UserGender {
    MALE, FEMALE
  }

  private enum  UserStatus {
    ACTIVE, INACTIVE
  }

  private int id;
  private String name;
  private String email;
  private String gender;
  private String status;

  public static String sorteiaGenero(){
    return UserGender.values()[new Random().nextInt(UserGender.values().length)].toString().toLowerCase();
  }

  public static String sorteiaStatus(){
    return UserStatus.values()[new Random().nextInt(UserStatus.values().length)].toString().toLowerCase();
  }
}
