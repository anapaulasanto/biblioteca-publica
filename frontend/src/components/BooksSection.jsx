import axios from "axios"
import { useState, useEffect } from "react"
import BooksCard from "./BooksCard"

export default function BooksSection() {
    const [books, setBooks] = useState([])

    async function handleView() {
        const url = 'http://localhost:8081/biblioteca-publica/livros/buscar?titulo=world'
        let response = await axios.get(url)
        let data = response.data
        let books = data
        setBooks(books)
    }

    useEffect(() => {
        handleView()
    }, []);
    
    return (
        <section>
            <div>
                <div class="row d-flex justify-content-center mt-5">
                    <BooksCard books={books} />
                </div>
            </div>
        </section>


    )
}