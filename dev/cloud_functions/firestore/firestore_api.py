import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

class FirestoreAPI:
    """FirestoreAPIクラス
    """
    def __init__(self, credentials_path: str) -> None:
        """コンストラクタ

        Args:
            credentials_path (str): 秘密鍵のパス
        """
        cred = credentials.Certificate(credentials_path)
        firebase_admin.initialize_app(cred)
        self.db = firestore.client()
        
    def create(self, collection: any, data: any):
        """指定したコレクションにデータを追加する

        Args:
            collection (any): コレクション名
            data (any): 追加するデータ名
        """
        self.db.collection(collection).add(data)

    def read(self, collection, document_id) -> any:
        """指定したコレクション&ドキュメントからデータを取得する

        Args:
            collection (any): コレクション名
            document_id (any): 読み取り先のコレクションid

        Returns:
            any: 読み取り結果
        """
        return self.db.collection(collection).document(document_id).get().to_dict()

    def update(self, collection: any, document_id: any, data: any) -> None:
        """指定したコレクション&ドキュメントのデータを更新する

        Args:
            collection (any): コレクション名
            document_id (any): ドキュメントid
            data (any): 更新データ
        """
        self.db.collection(collection).document(document_id).update(data)

    def delete(self, collection: any, document_id: any):
        """指定したコレクション&ドキュメントのデータを削除する

        Args:
            collection (any): コレクション名
            document_id (any): 削除するドキュメントid
        """
        self.db.collection(collection).document(document_id).delete()