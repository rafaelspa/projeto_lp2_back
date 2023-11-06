package spo.ifsp.edu.br.projeto_lp2.models;


import jakarta.persistence.*;

@Entity
@Table(name = "timezones")
public class Timezone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timezone_offset")
    private String offset;
    private String description;

    public Timezone() {
    }
    public Timezone(String offset, String description) {
        this.offset = offset;
        this.description = description;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
