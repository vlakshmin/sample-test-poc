package api.entities.auth;

import api.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String mail;
    private String password;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
