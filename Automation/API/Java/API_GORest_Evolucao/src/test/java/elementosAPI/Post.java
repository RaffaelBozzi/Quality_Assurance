package elementosAPI;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
  private int id;
  private int user_id;
  private String title;
  private String body;
}
