package com.macinternetservices.sablebusinessdirectory;

public class SearchListItems {
        private String searchListItem;

        public SearchListItems(String category) {
            this.searchListItem = category;
        }

        public String getSearchListItem() {
            return this.searchListItem;
        }
}
