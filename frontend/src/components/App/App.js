import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom'
import Books from '../Books/BookList/books';
import BooksService from "../../repository/booksRepository";
import Countries from "../Countries/countries";
import Authors from "../Authors/authors";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            countries: [],
            authors: [],
            books: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/countries"} element={<Countries countries={this.state.countries}/>}/>
                            <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"/books/add"}
                                   element={<BookAdd authors={this.state.authors} categories={this.state.categories}
                                                     onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"}
                                   element={<BookEdit authors={this.state.authors}
                                                      categories={this.state.categories}
                                                      onEditBook={this.editBook}
                                                      book={this.state.selectedBook}/>}/>
                            <Route path={"/books"}
                                   element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} onMarkAsTaken={this.markAsTaken}/>}/>
                            <Route path={"/"} element={<Books books={this.state.books}/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadCountries();
        this.loadCategories();
        this.loadBooks();
    }

    loadCategories = () => {
        BooksService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadCountries = () => {
        BooksService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadAuthors = () => {
        BooksService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadBooks = () => {
        BooksService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    deleteBook = (id) => {
        BooksService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        BooksService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        BooksService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        BooksService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    markAsTaken = (id) => {
        BooksService.markAsTaken(id)
            .then(() => {
                this.loadBooks();
            })
    }


}

export default App;
