```mermaid
erDiagram
    users ||--o{ comments : "1人のユーザーに0以上のコメント"
    users ||--o{ prompts : "1人のユーザーに0以上のプロンプト"
    users ||--O{ user_lesson : "1人のユーザーに0以上のクラス"
    lessons ||--O{ user_lesson : "1つのクラスに0以上のユーザー"
    prompts ||--o{ comments : "1つのプロンプトに0以上のコメント"
    lessons ||--o{ prompts : "1つのクラスに0以上のプロンプト"

    users {
        bigint id PK
        string name "ユーザーネーム"
        timestamp created_at
        timestamp deleted_at
    }

    lessons {
        bigint id PK
        string name "ユーザーネーム"
        string category "カテゴリー"
    }

    prompts {
        bigint id PK
        string prompts "プロンプト"
        references owner FK "user_id"
        references lesson FK "lesson_id"
        timestamp created_at
        timestamp modified_at
    }

    comments {
        bigint id PK
        references owner FK "user_id"
        references prompt FK "prompt_id"
        string content "コメント内容"
        timestamp created_at
        timestamp deleted_at
    }

    user_lesson {
        bigint id PK
        references user FK "user_id"
        references lesson FK "lesson_id"
    }
  
```
