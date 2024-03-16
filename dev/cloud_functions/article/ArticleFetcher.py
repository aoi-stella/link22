from api.feedparser.feedparser_api import FeedParserAPI

class ArticleFetcher:
    def __init__(self, feedparser_api: FeedParserAPI) -> None:
        """コンストラクタ

        Args:
            feedparser_api (FeedParserAPI): RSSフィードをパースするAPI
        """
        self.feedparser_api = feedparser_api
        
    def fetch(self) -> any:
        """指定されたURLのRSSフィードを取得する

        Args:
            url (str): RSSフィードのURL

        Returns:
            any: RSSフィードのパース結果
        """
        return self.feedparser_api.parse()