import BooksSection from "../components/BooksSection";
import NavBar from "../components/NavBar";

let books = [
    { title: "Book 1", author: "Author 1", description: "Description 1", avaliation: 4.5 },
    { title: "Book 2", author: "Author 2", description: "Description 2", avaliation: 4.0 },
    { title: "Book 3", author: "Author 3", description: "Description 3", avaliation: 4.8 },
    { title: "Book 4", author: "Author 4", description: "Description 4", avaliation: 4.2 },
    { title: "Book 5", author: "Author 5", description: "Description 5", avaliation: 4.7 },
    { title: "Book 6", author: "Author 6", description: "Description 6", avaliation: 4.1 }
]

export default function Books() {
    return (
        <div>
            <NavBar />
            <BooksSection books={books} />
        </div>
    )
}