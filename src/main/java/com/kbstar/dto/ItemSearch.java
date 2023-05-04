package com.kbstar.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ItemSearch {

    private String name;
    private int price;
    private int min_price;
    private int max_price;
}
