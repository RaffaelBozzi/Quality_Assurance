package elementosAPI;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
  private int id;
  private int post_id;
  private String name;
  private String email;
  private String body;
}
