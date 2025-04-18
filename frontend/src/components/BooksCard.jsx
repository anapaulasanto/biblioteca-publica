import { useNavigate } from "react-router-dom"
import FormatedTitle from "../functions/FormatedTitle"

export default function BooksCard({ books }) {
    const Navigate = useNavigate()

    const bookDetails = (book) => {
        const title = FormatedTitle(book.titulo)
        Navigate(`/book/${title}`, { state: book })
    }

    return (
        <div class="row d-flex justify-content-center  mt-3 ">
            <h1 class="title mb-5">Top avaliados</h1>
            {books.map((book, index) => (
                <div key={index} class="card me-5 col-sm-6 mb-sm-0 col-md-4 col-lg-2 mb-lg-3 pb-lg-5 border border-0 border-bottom "  onClick={() => bookDetails(book)} >
                    <div class="card-body  mx-auto d-block " role="button">
                        <div class="border border-0  h-100">
                            <img class="h-75 pb-3  object-fit-fill" src={book.capa} alt="" />
                            <h5 className="card-title">{book.titulo}</h5>
                            <p class="text-uppercase" className="card-text">{book.autores}</p>
                            <p className="card-text pb-3">{book.data}</p>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    )
}