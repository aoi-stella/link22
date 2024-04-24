from api.firestore.firestore_api import FirestoreAPI
from article.ArticleDataClass import Article
from datetime import datetime

"""記事データ操作クラス

firestore内に保存されているコレクションに対するCRUD操作を行うクラス。
RSSフィードから実際に取得する処理はArticleFetcherクラスで行うこととする。
"""
class ArticleRepository:
    # コレクション名
    COLLECTION_NAME = 'articles'
    
    def __init__(self, firestore_api: FirestoreAPI):
        """コンストラクタ

        Args:
            firestore_api (FirestoreAPI): APIクラス
        """
        self.firestore_api = firestore_api

    def save(self, article: Article):
        """記事を保存する

        Args:
            article (Article): 記事データ
        """
        #TODO: 参照先のURLをどこかで共通化したいな
        publisher_name_dict = {
            "https://feeds.feedburner.com/TheHackersNews":"TheHackersNews",
            "url1":"publisher1",
            "url2":"publisher2",
            "url3":"publisher3",
            "url4":"publisher4",
        }
        article.publisher = publisher_name_dict[article.publishFrom]
        date_obj = datetime.strptime(article.publishDate, '%a, %d %b %Y %H:%M:%S %z')
        article.publishDate = date_obj.strftime('%Y/%m/%d')
        self.firestore_api.create(self.COLLECTION_NAME, article.__dict__)

    def get(self, article_id: str) -> Article:
        """指定されたIDの記事を取得する

        Args:
            article_id (str): 取得する記事のID

        Returns:
            Article: 記事データ
        """
        data = self.firestore_api.read(self.COLLECTION_NAME, article_id)
        return Article(data['title'], data['link'], data['summary'])

    def update(self, article_id: str, article: Article):
        """指定されたIDの記事を更新する

        Args:
            document_id (str): ドキュメントid
            article (Article): 記事データ
        """
        self.firestore_api.update(self.COLLECTION_NAME, article_id, article.__dict__)

    def delete(self, article_id: str):
        """指定されたIDの記事を削除する

        Args:
            article_id (str): 記事ID
        """
        self.firestore_api.delete(self.COLLECTION_NAME, article_id)