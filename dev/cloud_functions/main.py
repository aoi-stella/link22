from article.ArticleDataClass import Article
from article.ArticleFetcher import ArticleFetcher
from article.ArticleRepository import ArticleRepository
from api.firestore.firestore_api import FirestoreAPI
from api.feedparser.feedparser_api import FeedParserAPI
from api.cloud_translation.cloud_translation_api import CloudTranslationAPI
from api.bq.bq_api import BigQueryAPI

# TODO: URLはどこかで共通化したいな
url = "https://feeds.feedburner.com/TheHackersNews"

def __setupArticleRepository() -> ArticleRepository:
   """ArticleRepositoryをセットアップする

   Returns:
       ArticleRepository: ArticleRepositoryのインスタンス
   """
   return ArticleRepository(FirestoreAPI("./firebase-key.json"), CloudTranslationAPI())

def __setupAritcleFetcher() -> ArticleFetcher:
   """ArticleFetcherをセットアップする

   Returns:
       ArticleFetcher: ArticleFetcherのインスタンス
   """
   return ArticleFetcher(FeedParserAPI(url))

def __setupBq(projectId: str) -> BigQueryAPI:
   return BigQueryAPI(projectId)

def main(data = None, context = None):
   PROJECT_ID = "link22-dev-01"
   articleRepository = __setupArticleRepository()
   articleFetcher = __setupAritcleFetcher()
   bqApi = __setupBq(PROJECT_ID)
   bqApi.clear_table("articles_tag_tbl")
   
   articleRepository.delete_all()
   feeds = articleFetcher.fetch()
   for article in feeds['entries']:
      newArticle = Article(
         article['title'],
         article['link'],
         article['summary'],
         url,
         article['published'],
         article['links'][1]['href'])
      
      newData = [
         {"contents": article.summary }
      ]
      # 記事をリストへ追加する
      articleRepository.save(newArticle)
      # bqへ記事データを追加
      bqApi.insert_to_row("articles_tag_tbl", newData)
   bqApi.classify_articles(PROJECT_ID, "articles_tag_tbl")
   
# エントリーポイント
if __name__ == "__main__":
   main()