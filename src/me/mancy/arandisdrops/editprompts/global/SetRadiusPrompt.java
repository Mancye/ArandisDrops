package me.mancy.arandisdrops.editprompts.global;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;

public class SetRadiusPrompt extends NumericPrompt {

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
        Settings.setDropHeight(number.doubleValue());
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return new FormattedMessage(ChatColor.GRAY + "Enter a new drop radius, in number of blocks: ").toString();
    }
}
