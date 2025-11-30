package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @NotBlank(message = "Nome do produto é obrigatório")
    @Column(nullable = false)
    String name;

    @NotBlank(message = "Nome do Fabricante é obrigatório")
    @Column(nullable = false)
    String manufacturer;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço mínimamente maior que zero")
    @Column(nullable = false)
    BigDecimal price;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade não pode ser 0 ou menor")
    @Column(nullable = false)
    Integer quantity;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
    @Column(nullable = false)
    String description;
}
