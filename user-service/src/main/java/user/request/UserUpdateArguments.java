package user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateArguments {

    @NotNull
    public Long id;

    @NotNull
    @Size(min = 6, max = 45)
    public String password;

    @NotNull
    public String email;

    @NotNull
    @Size(min = 3, max = 20)
    public String name;
}
