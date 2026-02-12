from flask import Flask, render_template, url_for, redirect, request
from sqlalchemy import create_engine, String, Integer, Column
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import uuid

# -----------------------------------------------------DATABASE---------------------------------------------------------#

Base = declarative_base()


def generate_uuid():
    return str(uuid.uuid4())


class Users(Base):
    __tablename__ = "book"
    bookId = Column("bookID", String, primary_key=True, default=generate_uuid)
    book = Column("book", String)
    author = Column("author", String)
    rating = Column("rating", Integer)

    def __init__(self, book, author, rating):
        self.book = book
        self.author = author
        self.rating = rating


db = "sqlite:///library.db"
engine = create_engine(db)
Base.metadata.create_all(engine)
Session = sessionmaker(bind=engine)
session = Session()

# --------------------------------------------------SERVER--------------------------------------------------------------#


app = Flask(__name__)

all_books = []


@app.route("/")
def home():
    return render_template("index.html", books=all_books)


@app.route("/add", methods=["GET", "POST"])
def add():
    if request.method == "POST":
        data = {
            "title": request.form["book"],
            "author": request.form["author"],
            "rating": request.form["rating"]
        }
        user = Users(data["title"], data["author"], data["rating"])
        session.add(user)
        session.commit()
        all_books.append(data)
        return redirect(url_for('home'))

    return render_template("add.html")


if __name__ == "__main__":
    app.run(debug=True)
