package elementosAPI;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
  private String field;
  private String message;
}
