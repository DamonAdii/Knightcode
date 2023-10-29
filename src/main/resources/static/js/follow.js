    $(document).ready(function() {
        $('.follow-button').click(function() {
            alert("yes follow btn is clicked");
            var userId = $(this).attr('id').replace('followButton_', '');
            followUser(userId);
        });

        $('.unfollow-button').click(function() {
            var userId = $(this).attr('id').replace('unfollowButton_', '');
            unfollowUser(userId);
        });
    });

    function followUser(userId) {
        alert("I'm ready to follow this user with ID: " + userId);
        // Send a POST request to follow the user
        fetch('/api/follow/' + userId, { method: 'POST' })
            .then(response => {
                if (response.status === 200) {
                    // Successfully followed the user, update the button
                    alert("Successfully followed");
                    location.reload(); // Reload the page
                    $('#followButton_' + userId).hide();
                    $('#unfollowButton_' + userId).show();
                }
                else
                {
                  alert("Follow failed");
                }
            });
    }

    function unfollowUser(userId) {
        // Send a POST request to unfollow the user
        fetch('/api/unfollow/' + userId, { method: 'POST' })
            .then(response => {
                if (response.status === 200) {
                    // Successfully unfollowed the user, update the button
                    alert("Successfully unfollowed");
                    location.reload(); // Reload the page
                    $('#followButton_' + userId).show();
                    $('#unfollowButton_' + userId).hide();
                }
                else
                {
                alert("Failed unfollowed");
                }
            });
    }