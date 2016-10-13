package user.request;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class UserCreation {
    @NotNull
    @Size(min = 3, max = 15)
    public String login;

    @NotNull
    @Size(min = 6, max = 45)
    public String password;

    @NotNull
    public String email;

    @NotNull
    @Size(min = 3, max = 20)
    public String name;
}
