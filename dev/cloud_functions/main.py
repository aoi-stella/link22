from article.ArticleRepository import ArticleRepository
from firestore.firestore_api import FirestoreAPI
from article.ArticleDataClass import Article

url = "https://feeds.feedburner.com/TheHackersNews"

# エントリーポイント
if __name__ == "__main__":
   article_repository = ArticleRepository(FirestoreAPI("../key/firebase-key.json"))
   article_repository.save(Article("title", "link", "summary"))