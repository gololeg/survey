package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {
    private Long id;
    private String name;

    private String text;
    private String value;
    private boolean isRight;
    private long rowTextNum;

    public long getRowTextNum() {

        return getText().lines().count();
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnswerDto other = (AnswerDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
