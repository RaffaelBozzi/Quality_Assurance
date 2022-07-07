package users;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.Objects;

public class UserConstructor {
    private final String name;
    private final String email;
    private final String gender;
    private final String status;

    @Override
    public String toString() {
        return "UserConstructor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public UserConstructor(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConstructor that = (UserConstructor) o;
        return Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(gender.toLowerCase(), that.gender.toLowerCase())
                && Objects.equals(status.toLowerCase(), that.status.toLowerCase());
    }

    public JSONObject toJSONObject() {
        return new JSONObject(new Gson().toJson(this));
    }
}
