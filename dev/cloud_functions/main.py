from article.ArticleRepository import ArticleRepository
from firestore.firestore_api import FirestoreAPI
from article.ArticleDataClass import Article

url = "https://feeds.feedburner.com/TheHackersNews"

def __setupArticleRepository() -> ArticleRepository:
   return ArticleRepository(FirestoreAPI("../key/firebase-key.json"))

# エントリーポイント
if __name__ == "__main__":
   articleRepository = __setupArticleRepository()
   articleRepository.save(Article("title", "link", "summary"))