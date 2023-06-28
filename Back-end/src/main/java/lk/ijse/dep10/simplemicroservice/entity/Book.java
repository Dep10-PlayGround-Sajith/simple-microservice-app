package lk.ijse.dep10.simplemicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {
    private String isbn;

    @NotNull(message = "Title must be between 2 to 15 characters")
    private String title;

}
