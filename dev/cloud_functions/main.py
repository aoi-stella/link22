from article.ArticleDataClass import Article
from article.ArticleFetcher import ArticleFetcher
from article.ArticleRepository import ArticleRepository
from api.firestore.firestore_api import FirestoreAPI
from api.feedparser.feedparser_api import FeedParserAPI

url = "https://feeds.feedburner.com/TheHackersNews"

def __setupArticleRepository() -> ArticleRepository:
   """ArticleRepositoryをセットアップする

   Returns:
       ArticleRepository: ArticleRepositoryのインスタンス
   """
   return ArticleRepository(FirestoreAPI("../key/firebase-key.json"))

def __setupAritcleFetcher() -> ArticleFetcher:
   """ArticleFetcherをセットアップする

   Returns:
       ArticleFetcher: ArticleFetcherのインスタンス
   """
   return ArticleFetcher(FeedParserAPI(url))

# エントリーポイント
if __name__ == "__main__":
   
   articles = []
   articleRepository = __setupArticleRepository()
   articleFetcher = __setupAritcleFetcher()
   feeds = articleFetcher.fetch()
   for article in feeds['entries']:
      articles.append(Article(article['title'], article['link'], article['summary']))
   # 記事をリストへ追加する
   #articleRepository.save(Article(article['title'], article['link'], article['summary']))
   exit(0)