
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restapicallwithcaching.data.model.RepoOwner

@Entity(tableName = "repos")
data class RepoItem(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val description: String?,
    val updatedAt: String?,
    val stargazersCount: Int?,
    val owner: RepoOwner?
)