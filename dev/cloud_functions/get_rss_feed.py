import feedparser
import pprint

url = "https://feeds.feedburner.com/TheHackersNews"

class Article:
   """記事クラス
   """
   def __init__(self, title: str, link: str, summary: str):
      """コンストラクタ

      Args:
         title (str): 記事のタイトル
         link (str): 記事のリンク
         summary (str): 記事の概要
      """
      self.title = title
      self.link = link
      self.summary = summary

def get_article(url: str) -> list[Article]:
   """指定されたURLのRSSフィードから記事を取得する

   Args:
       url (str): RSSフィード取得先のURL

   Returns:
       list[Article]: 記事のリスト
   """
   articles = []
   # 記事を取得
   feeds = feedparser.parse(url)
   for article in feeds['entries']:
      title = article['title']
      link = article['link']
      summary = article['summary']
      
      # 記事をリストへ追加する
      articles.append(Article(title, link, summary))
   return articles
   
# エントリーポイント
if __name__ == "__main__":
   articles = get_article(url)
   print(articles[0].title)