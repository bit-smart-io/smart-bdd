/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.bookstore.controller;

import com.example.bookstore.model.IsbnBook;
import com.example.bookstore.service.BookIsbnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Additional endpoint required.
 * /get-price/{isbn}
 *
 * Notes:
 * 	// get book (IsbnBook) from IsbnBookDB
 * 	// get books (BookVendorSummary) from OldBookSellerAPI and NewBookSellerAPI
 * 	// BookEnquiry = all fields from or the 2 objects BookIsbnData + BookIsbnData
 *
 * 	// workflow
 * 	// all book-sellers return BookVendorSummary (isbns, price and condition (new, old))
 * 	// for each isbn call IsbnBookDB and return IsbnBook
 * 	// return to the user CustomerEnquiry list of BookEnquiry
 *
 * 	// /book/{isbn} returns BookIsbnData.             or /book/{isbn}
 * 	// /books/enquiry/{isbn} returns CustomerEnquiry. or /books/{isbn}
 */
@RestController
public class BookController {
	private final BookIsbnService service;

	public BookController(BookIsbnService service) {
		this.service = service;
	}

	@GetMapping("/book")
	public String book() {
		return "Book";
	}

	@GetMapping(value = "/book/{isbn}", produces = "application/json")
	public @ResponseBody
	IsbnBook bookByIsbn(@PathVariable String isbn) throws IOException {
		return service.get(isbn);
	}
}
