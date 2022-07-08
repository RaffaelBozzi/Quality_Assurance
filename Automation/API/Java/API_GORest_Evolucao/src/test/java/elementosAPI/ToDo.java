package elementosAPI;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Random;

@Data
@Builder
public class ToDo {
  private enum ToDoStatus {
    PENDING, COMPLETED
  }

  private int id;
  private int user_id;
  private String title;
  private Date due_on;
  private String status;

  public static String sorteiaStatus(){
    return ToDoStatus.values()[new Random().nextInt(ToDoStatus.values().length)].toString().toLowerCase();
  }
}
