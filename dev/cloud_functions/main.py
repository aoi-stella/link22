from article.ArticleDataClass import Article
from article.ArticleFetcher import ArticleFetcher
from article.ArticleRepository import ArticleRepository
from api.firestore.firestore_api import FirestoreAPI
from api.feedparser.feedparser_api import FeedParserAPI

# TODO: URLはどこかで共通化したいな
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

def main():
   articleRepository = __setupArticleRepository()
   articleFetcher = __setupAritcleFetcher()
   articleRepository.delete_all()
   feeds = articleFetcher.fetch()
   for article in feeds['entries']:
      # 記事をリストへ追加する
      articleRepository.save(Article(
         article['title'],
         article['link'],
         article['summary'],
         url,
         article['published'])
      )
   
# エントリーポイント
if __name__ == "__main__":
   main()