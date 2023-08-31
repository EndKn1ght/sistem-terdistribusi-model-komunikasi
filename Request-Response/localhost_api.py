from flask import Flask, jsonify
import random

app = Flask(__name__)
quotes = [
    {
        "quote": "The only way to do great work is to love what you do.",
        "author": "Steve Jobs"
    },
    {
        "quote": "In three words I can sum up everything I've learned about life: it goes on.",
        "author": "Robert Frost"
    },
    {
        "quote": "The future belongs to those who believe in the beauty of their dreams.",
        "author": "Eleanor Roosevelt"
    }
]


@app.route('/quote', methods=['GET'])
def quote():
    random_quote = random.choice(quotes)
    return jsonify(random_quote)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
