package users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLombok {
  private String name;
  private String email;
  private String gender;
  private String status;
}
