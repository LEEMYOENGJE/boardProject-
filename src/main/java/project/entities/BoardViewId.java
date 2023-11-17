package project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // getter, setter, EqualsAndHashcode, toString
@AllArgsConstructor @NoArgsConstructor
public class BoardViewId {
    private Long seq;
    private Integer uid;

}
