import feedparser

class FeedParser:
    def __init__(self, url: str):
        """コンストラクタ

        Args:
            url (str): RSSフィードのURL
        """
        self.url = url

    def parse(self) -> any:
        """RSSフィードをパースする

        Returns:
            any: RSSフィードのパース結果
        """
        return feedparser.parse(self.url)