from google.cloud import translate_v2 as translate

class CloudTranslationAPI:
    """CloudTranslationAPIクラス
    """
    KEY_TRANSLATED_TEXT = "translatedText"
    
    client: translate.Client
    
    def __init__(self) -> None:
        """コンストラクタ
        """
        global client
        client = translate.Client()
        
    def translate(self, target_text: str) -> str:
        """指定されたテキストを翻訳する

        Args:
            target_text (str): 翻訳対象テキスト

        Returns:
            str: 翻訳語の文字列
        """
        global client
        res = client.translate(values=target_text, target_language="ja", source_language="en")
        return res[self.KEY_TRANSLATED_TEXT]