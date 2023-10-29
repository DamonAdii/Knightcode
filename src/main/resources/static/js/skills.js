$(function () {
    $("#searchInput").autocomplete({
        source: function (request, response) {
            // Make an AJAX call to the server to fetch skill suggestions based on the user's input
            $.ajax({
                url: "/search-skills",
                type: "GET",
                data: {
                    skillName: request.term
                },
                success: function (data) {
                    response(data.map(function (item) {
                        return {
                            label: item.name,         // The skill name
                            value: item.name,         // The skill name
                            image: item.skillImageUrl,     // The image URL
                            // Render the HTML for each search result item
                            html: "<div><img src='" + item.skillImageUrl + "' alt='" + item.name + "'> " + item.name + "</div>"
                        };
                    }));
                }
            });
        },
        select: function (event, ui) {
            var selectedSkill = ui.item.label;
            // Add the selected skill and its image to the list of selected skills
            $("#selectedSkills").append("<li><img src='" + ui.item.skillImageUrl + "' alt='" + selectedSkill + "'> " + selectedSkill + " <button class='removeSkill'>Remove</button></li>");
            $("#searchInput").val("");
            return false;
        }
    });

    $("#selectedSkills").on("click", "li .removeSkill", function () {
        $(this).parent().remove();
    });

    $("#saveChanges").click(function () {
        var selectedSkills = [];
        $("#selectedSkills li").each(function () {
            var skillName = $(this).text().trim().split(' ')[0]; // Extract only the skill name
            selectedSkills.push(skillName);
        });

        // Send an AJAX request to update the user's skills
        $.ajax({
            url: "/update-skills",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(selectedSkills),
            success: function (data) {
                alert("Skills updated successfully.");
                location.reload(); // Reload the page
            },
            error: function () {
                alert("Failed to update skills.");
            }
        });
    });

    // Remove updated skills on click
    $("#updatedSkills").on("click", ".removeUpdatedSkill", function () {
        var skillName = $(this).text();
        var skillToRemove = skillName.trim();

        $.ajax({
            url: "/remove-updated-skill", // Adjust the URL to match your endpoint
            type: "POST",
            data: {
                skillName: skillToRemove
            },
            success: function (data) {
                alert(data); // Show a success message
                $(this).parent().remove(); // Remove the skill tag from the UI
                location.reload(); // Reload the page
            },
            error: function () {
                alert("Failed to remove updated skill.");
            }
        });
    });


});
