import { useNavigate } from "react-router-dom"
import FormatedTitle from "../functions/FormatedTitle"

export default function BooksCard({ books }) {
    const Navigate = useNavigate()

    const bookDetails = (book) => {
        const title = FormatedTitle(book.titulo)
        Navigate(`/book/${title}`, { state: book })
    }

    return (
        <div class="row d-flex justify-content-center mt-5 ">
            <h1 class="text-center">Top avaliados..</h1>
            {books.map((book, index) => (
                <div key={index} class="card me-1 col-sm-6 mb-sm-0 col-md-2 col-lg-3 mb-lg-5 pb-lg-5 border border-0 border-bottom "  onClick={() => bookDetails(book)} >
                    <div class="card-body w-100 h-100 mx-auto d-block border border-light-subtle pb-5 " role="button">
                        <img class="h-75  mx-auto d-block object-fit-scale" src={book.capa} alt="" />
                        <h5 className="card-title fs-6">{book.titulo}</h5>
                        <p className="card-text">{book.autores}</p>
                        <p className="card-text pb-3">{book.data}</p>
                    </div>
                </div>
            ))}
        </div>
    )
}