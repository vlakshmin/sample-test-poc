package api.entities;

import api.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    private String mail;
    private String password;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
