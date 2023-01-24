package com.rest.books.bootrestbooks.Payloads;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRespDto extends AuthorDto{

    private int authorId;

}
