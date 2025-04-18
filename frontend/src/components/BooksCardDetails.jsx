export default function BooksCardDetails({ book }) {
    return (
        <div>
            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src={book.capa} class="img-fluid rounded-start" alt="..." />
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">{book.titulo}</h5>
                            <p class="card-text">{book.autores}</p>
                            <p class="card-text">{book.data}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}