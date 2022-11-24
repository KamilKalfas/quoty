package com.kkalfas.quoty.mocks.response

object ResponseQuoteOfTheDay {
    operator fun invoke() =
        """
            {
              "qotd_date": "2014-07-04T03:00:00.000-05:00",
              "quote": {
                "id": 17025,
                "favorites_count": 0,
                "dialogue": false,
                "favorite": false,
                "tags": [
                  "equality",
                  "men"
                ],
                "url": "http://localhost:3000/quotes/abraham-lincoln/17025-fourscore-and-",
                "upvotes_count": 1,
                "downvotes_count": 0,
                "author": "Abraham Lincoln",
                "author_permalink": "abraham-lincoln",
                "body": "Fourscore and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal."
              }
            }
        """.trimIndent()
}
