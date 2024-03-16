from api.firestore.firestore_api import FirestoreAPI
from article.ArticleDataClass import Article

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